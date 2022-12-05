from SocketClient import SocketClient
from SocketServer import SocketServer
from Match import Match

class AppThem():


    def main():
        try:
            idMatch = 1;
            client = SocketClient("localhost", 12002)
            print("Cliente 'Them' concluido!\n")
            server = SocketServer(12003, idMatch)
            print("Servidor 'Them' concluido!\n")

            match = Match(1, 15, client, server)
            res: int = match.playMatch()
        except Exception as e:
            print("Error: " + str(e));

    if __name__ == "__main__":
        main()
