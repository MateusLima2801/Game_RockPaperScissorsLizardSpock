from SocketClient import SocketClient
from SocketServer import SocketServer
from Match import Match
from Message import Message
import socket
from Board import Board
from ePiece import ePiece

class App():
    def main():
        try:
            idMatch = 1;
            server = SocketServer(12002,idMatch);
            print("Servidor 'Me' concluido!");
            client = SocketClient("localhost", 12003)
            print("Cliente 'Me' concluido!")

            match = Match(1, 15, client, server)
            res: int = match.playMatch()
            winner = ""
            if res>0:
                winner = "Me";
            elif res==0:
                winner = "No one";
            else: winner = "Them";

            print(winner + " has won the match " + str(idMatch))
        except Exception as e:
            print("Error: " + str(e))

    if __name__ == "__main__":
        main()
