import SocketClient
import SocketServer
import Match

class App():
    if __name__ == "__main__":
        main()

    def main():
        try:
            idMatch = 1;
            server = SocketServer(12345,1);
            print("Servidor 'Me' concluido!");
            client = SocketClient("127.0.0.1", 12346)
            print("Cliente 'Me' concluido!")

            match = Match(1, 5, client, server)
            res: int = match.playMatch()
            winner = ""
            if res>0:
                winner = "Me";
            elif res==0:
                winner = "No one";
            else: winner = "Them";
            print(winner + " has won the match " + idMatch);
        except Exception as e:
            print("Error: " + e + "\n");