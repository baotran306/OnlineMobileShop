import smtplib
import ssl
import random
# from email.mime.text import MIMEText


port = 587  # For starttls
smtp_server = "smtp.gmail.com"
sender_email = "confirmemail.bao"
receiver_email = "n18dccn211@student.ptithcm.edu.vn"
password = input("Type your password and press enter:")
verify_number = random.randint(100000, 999999)
print(verify_number)
message = """\
Subject: Xác thực email đăng kí tài khoản từ BaoThangShop.

This message is sent from Python
Mã xác thực của bạn là: {}
Vui lòng nhập đúng mã xác thực để kích hoạt tài khoản.
.""".format(verify_number).encode("utf-8")

# html = """\
# <html>
#   <body>
#     <p>Hi,<br>
#        How are you?<br>
#        <a href="http://www.realpython.com">Real Python</a>
#        has many great tutorials.
#     </p>
#   </body>
# </html>
# """
# message = MIMEText(html, "html")

context = ssl.create_default_context()
with smtplib.SMTP(smtp_server, port) as server:
    server.starttls(context=context)
    server.login(sender_email, password)
    server.sendmail(sender_email, receiver_email, message)
