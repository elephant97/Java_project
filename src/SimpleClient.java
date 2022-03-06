import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        InputStream in_str;
        OutputStream out_str;
        String in_msg = null;
        BufferedReader buf_in;
        BufferedReader buf_out;
        BufferedWriter buf_wri = null;
        PrintWriter pr_wri = null;
        String out_msg = null;

        try{
            Socket sock_1 = new Socket("127.0.0.1",5434);
            in_str = sock_1.getInputStream();
            out_str = sock_1.getOutputStream();
            buf_in = new BufferedReader(new InputStreamReader(in_str));
            buf_out = new BufferedReader(new InputStreamReader(System.in));
            buf_wri = new BufferedWriter(new OutputStreamWriter(out_str));
            pr_wri = new PrintWriter(buf_wri,true);

            while(true)
            {
                in_msg = buf_in.readLine();
                System.out.println(in_msg);
                if(in_msg.equals("exit")) break;
                System.out.print("me:");
                out_msg=buf_out.readLine();
                if(out_msg.equals("exit")) {
                    pr_wri.println(out_msg);
                    break;
                }
                pr_wri.println("client:"+out_msg);
            }
            System.out.println("server와의 접속이 종료되었습니다.");
            pr_wri.close();
            sock_1.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
