<%@page contentType="text/html;charset=euc-kr" pageEncoding="euc-kr"%>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@page import="java.net.*" %>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="nl.captcha.Captcha" %>
<%@page import="nl.captcha.Captcha.Builder" %>
<%@page import="nl.captcha.backgrounds.FlatColorBackgroundProducer" %>
<%@page import="java.awt.Color" %>
<%
//������ ��õ ĸ��

//========= �� �Ķ���� �м� ���� [ ���� ] =====
String sign_mode   = request.getParameter("mode");
if(sign_mode == null)
{
	sign_mode = "";
}
//========= �� �Ķ���� �м� ���� [ ���� ] =====
if(sign_mode.equals("check"))
{
	//�ڵ���Ϲ��� �ڵ带 �����Ѵ�.
	String mAnswer = (String)request.getSession().getAttribute("IMAGE");
	%><%= mAnswer %><%
}else
{
	//�ڵ���Ϲ��� �ڵ带 �̹����� ����Ѵ�.
	Captcha mCaptcha;
	Builder mBuilder;
	String  mAnswer;

	mBuilder= new Captcha.Builder(160, 50);
	mBuilder.addText();
	mBuilder.addBackground(new FlatColorBackgroundProducer(Color.WHITE));
	mBuilder.addBorder();
	mBuilder.addNoise();
	mCaptcha = mBuilder.build();
	mAnswer = mCaptcha.getAnswer();

	OutputStream mOut = response.getOutputStream();
	ImageIO.write(mCaptcha.getImage(),"jpg", mOut);
	mOut.close();

	request.getSession().setAttribute("IMAGE",mAnswer);
}
%>