import Message
import ePiece
import socket

class SocketClient():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    #DataOutputStream dOut;

    def __init__(self, ip, port):
        server_addr = (ip, port)
        self.sock.connect(server_addr)
        # client = new Socket(ip,port);
        # dOut = new DataOutputStream(client.getOutputStream());
        mess = Message(1, 1, ePiece.PAPER);
        self.sendMessage(mess);
    

    def sendMessage(self, message): 
        jsonObj = message.createJsonMessage();
        self.sock.send(jsonObj.encode())
    

    def __del__(self): 
        self.sock.close()
