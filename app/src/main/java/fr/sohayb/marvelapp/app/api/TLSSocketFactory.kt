package fr.sohayb.marvelapp.app.api

import androidx.annotation.Nullable
import java.net.InetAddress
import java.net.Socket
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.net.ssl.*

class TLSSocketFactory : SSLSocketFactory() {

    var  delegate: SSLSocketFactory
    lateinit var trustManagers: Array<TrustManager>

    init {
        generateTrustManagers()
        val context: SSLContext = SSLContext.getInstance("TLS")
        context.init(null, trustManagers, null)
        delegate = context.getSocketFactory()
    }

    @Throws(KeyStoreException::class, NoSuchAlgorithmException::class)
    private fun generateTrustManagers() {
        val trustManagerFactory: TrustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(null as KeyStore?)
        val trustManagers: Array<TrustManager> = trustManagerFactory.getTrustManagers()
        check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
            ("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers))
        }
        this.trustManagers = trustManagers
    }

    override fun getDefaultCipherSuites(): Array<String> {
        return delegate.getDefaultCipherSuites();
    }

    override fun createSocket(s: Socket?, host: String?, port: Int, autoClose: Boolean): Socket {
        return enableTLSOnSocket(delegate.createSocket(s, host, port, autoClose));
    }

    override fun createSocket(host: String?, port: Int): Socket {
        return enableTLSOnSocket(delegate.createSocket(host, port));
    }

    override fun createSocket(
        host: String?,
        port: Int,
        localHost: InetAddress?,
        localPort: Int
    ): Socket {
        return enableTLSOnSocket(delegate.createSocket(host, port, localHost, localPort));
    }

    override fun createSocket(host: InetAddress?, port: Int): Socket {
        return enableTLSOnSocket(delegate.createSocket(host, port));
    }

    override fun createSocket(
        address: InetAddress?,
        port: Int,
        localAddress: InetAddress?,
        localPort: Int
    ): Socket {
        return enableTLSOnSocket(delegate.createSocket(address, port, localAddress, localPort));
    }

    override fun createSocket(): Socket {
        return enableTLSOnSocket(delegate.createSocket());
    }



    override fun getSupportedCipherSuites(): Array<String> {
        return delegate.getSupportedCipherSuites();
    }

    private fun enableTLSOnSocket(socket: Socket): Socket {
        if (socket != null && socket is SSLSocket) {
            socket.enabledProtocols = arrayOf("TLSv1.1", "TLSv1.2")
        }
        return socket
    }

    @Nullable
    fun getTrustManager(): X509TrustManager {
        return trustManagers[0] as X509TrustManager
    }
}