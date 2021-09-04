import smtplib
import ssl


port = 587  # For starttls
smtp_server = "smtp.gmail.com"
sender_email = "confirmemail.bao@gmail.com"
receiver_email = "n18dccn013@student.ptithcm.edu.vn"
password = input("Type your password and press enter:")
message = """\
Subject: Xác thực email đăng kí tài khoản từ e-shop.

This message is sent from Python 1111."""

context = ssl.create_default_context()
with smtplib.SMTP(smtp_server, port) as server:
    server.starttls(context=context)
    server.login(sender_email, password)
    server.sendmail(sender_email, receiver_email, message)
