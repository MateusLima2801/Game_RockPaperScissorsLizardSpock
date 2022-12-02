import socket
import string
import json
import ePiece
import Message

class  SocketServer():
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    address: tuple()
    buffer = []
    conn = None
    idMatch: int = 0;
    HOST = "localhost"

    def __init__(self,port, _idMatch):
        self.server.bind((HOST, port))
        self.server.listen()
        self.conn, address = self.server.accept()
        self.idMatch = _idMatch;
        print("Cliente " + address + " adicionado.")
    

    # def waitMessage():
    #     String message = dIn.readUTF();
    #     readMessage(message);

    def readMessage(self,message: string):
        dict = json.loads(message);
        if self.idMatch == dict["idMatch"] :
            idMatch = dict["idMatch"]
            turn = dict["turn"];
            adversaryPlay = ePiece.getByName(dict["adversaryPlay"]);
            mess = Message(idMatch, turn, adversaryPlay);
            self.buffer.append(mess);
        else: raise Exception("Wrong idMatch received");

    def checkBuffer(self, turn: int):
        for i in range(0, self.buffer.count,1):
            if self.buffer[i].turn == self.turn :
                them = self.buffer[i].adversaryPlay
                self.buffer.remove(self.buffer[i])
                return them;

        raise Exception("Some message was lost on the way");
    
    def __del__(self):
        self.conn.close()
        self.server.close()
