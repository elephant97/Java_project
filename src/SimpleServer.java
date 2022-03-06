import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        InputStream in_str;
        BufferedReader buf_in;
        BufferedReader buf_out;
        BufferedWriter buf_wri;
        OutputStream out_str;
        ServerSocket svr_sock;
        PrintWriter prin_wri = null;
        Socket sock_1 = null;
        String in_msg = null;
        String out_msg = null;

        try{
            svr_sock = new ServerSocket(5434);
            System.out.println("서버 실행 중..");
            sock_1 = svr_sock.accept();
            in_str=sock_1.getInputStream();
            out_str=sock_1.getOutputStream();
            buf_out=new BufferedReader(new InputStreamReader(System.in));
            buf_in=new BufferedReader(new InputStreamReader(in_str));
            buf_wri=new BufferedWriter(new OutputStreamWriter(out_str));
            prin_wri=new PrintWriter(buf_wri, true);
            prin_wri.println("server:접속을 환영합니다.");

            while(true){
                in_msg= buf_in.readLine();
                System.out.println(in_msg);
                if(in_msg.equals("exit")) break;
                System.out.print("me:");
                out_msg= buf_out.readLine();
                if(out_msg.equals("exit")) {
                    prin_wri.println(out_msg);
                    break;
                }
                prin_wri.println("sever:"+out_msg);
            }
            System.out.println("client와의 접속이 종료되었습니다.");
            prin_wri.close();
            sock_1.close();
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }
}
