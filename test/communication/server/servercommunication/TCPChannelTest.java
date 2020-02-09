package communication.server.servercommunication;

import communication.TCPChannel.TCPChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class TCPChannelTest {

    TCPChannel tcpChannelServer;
    TCPChannel tcpChannelClient;

    @BeforeEach
    void setUpServer(){
        this.tcpChannelServer = new TCPChannel(8080,true,"server");
        this.tcpChannelClient = new TCPChannel(8080,false,"client");
    }

    @Test
    void isAsServer() {
        assertFalse(this.tcpChannelClient.isAsServer());
    }

    @Test
    void getSocketBeforeCommiunication() {
        assertEquals(null,this.tcpChannelClient.getSocket());
    }

    @Test
    void setSocket() {
        this.tcpChannelServer.setSocket(new Socket());
        assertNotEquals(null,this.tcpChannelServer.getSocket());
    }

}