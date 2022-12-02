import SocketClient
import SocketServer
import Match

class AppThem():
    if __name__ == "__main__":
        main()

    def main():
        try:
            idMatch = 1;
            client = SocketClient("127.0.0.1", 12345)
            print("Cliente 'Them' concluido!\n")
            server = SocketServer(12346, 1)
            print("Servidor 'Them' concluido!\n")

            match = Match(1, 5, client, server)
            res: int = match.playMatch()
        except Exception as e:
            print("Error: " + e + "\n");