import string
import json
from ePiece import ePiece
from Message import Message
from socket import socket, AF_INET, SOCK_STREAM

class  SocketServer():
    
    def __init__(self,port: int, idMatch: int):
        self.server = socket(AF_INET, SOCK_STREAM)
        self.buffer = []
        self.HOST = "localhost"
        self.server.bind((self.HOST, port))
        self.server.listen()
        self.conn, address = self.server.accept()
        self.idMatch = idMatch;
        print("Cliente " + str(address) + " adicionado.")
    

    def waitMessage(self):
        data = self.conn.recv(4096)
        self.readMessage(data);

    def readMessage(self,message: str):
        dict = json.loads(message);
        if self.idMatch == dict["idMatch"] :
            idMatch = dict["idMatch"]
            turn = dict["turn"]
            adversaryPlay = ePiece.getByName(name = dict["adversaryPlay"])
            mess = Message(idMatch, turn, adversaryPlay)
            self.buffer.append(mess)
            a = 1
        else: raise Exception("Wrong idMatch received")

    def checkBuffer(self, turn: int)-> ePiece:
        for i in range(0, len(self.buffer),1):
            if self.buffer[i].turn == turn :
                them = self.buffer[i].adversaryPlay
                self.buffer.remove(self.buffer[i])
                return them;

        raise Exception("Some message was lost on the way");
    
    def __del__(self):
        self.conn.close()
        self.server.close()
