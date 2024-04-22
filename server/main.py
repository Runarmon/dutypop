import socket


def main():
    host = socket.gethostname()
    port = 6231
    server_socket = socket.socket()  
    server_socket.bind((host, port))
    server(server_socket)


def server(server_socket):
    print("2")
    server_socket.listen(1)
    conn, address = server_socket.accept()
    while True:
        data = conn.recv(1024).decode()
        print("from connected user: " + str(data))
        data = input(' -> ')
        conn.send(data.encode())  # send data to the client
    

if __name__ == '__main__':
    main()