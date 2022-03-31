package com.sparta.myblog2.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ContentsAlert {

    public static void init(HttpServletResponse response) {
        response.setContentType("text/html; charset=euc-kr");
        response.setCharacterEncoding("euc-kr");
    }



    public static void alert(HttpServletResponse response , String string) throws IOException {
        // 이때 contentType을 먼저하지 않으면, 한글이 깨질 수 있습니다.
        response.setContentType("text/html; charset=euc-kr");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('"+ string +"'); </script>");
        out.flush();
    }


}
