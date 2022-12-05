from Message import Message
from ePiece import ePiece
import socket

class SocketClient():
    

    def __init__(self, ip, port):
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_addr = (ip, port)
        self.sock.connect(server_addr)

    def sendMessage(self, message): 
        jsonObj = message.createJsonMessage();
        self.sock.send(jsonObj.encode("utf-8"))
    

    def __del__(self): 
        self.sock.close()
