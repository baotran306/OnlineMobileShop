import re
import hashlib
import smtplib
import ssl


def hash_password(password):
    my_key = 'guess_key'
    db_password = password + my_key
    new_pass = hashlib.md5(db_password.encode())
    return new_pass.hexdigest()


def check_regex_password(your_pass):
    if " " in your_pass:
        print("----False Regex Password(Type 1)----")
        return False
    punctuation = "!\"#$%&'()*+,-/:;<=>?[\\]^`{|}~"
    for pun in punctuation:
        if pun in your_pass:
            print("----False Regex Password(Type 2)----")
            return False
    regex_type = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$"
    if re.search(regex_type, your_pass):
        return True
    print("----False Regex Password----")
    return False


def check_positive_number(num):
    if num < 0:
        print('----Deny negative number----')
        return False
    return True


def check_str_all_num(your_str):
    if your_str.isdigit():
        return True
    print('----Deny phone number or identity card contains character----')
    return False
