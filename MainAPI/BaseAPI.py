from flask import Flask, jsonify, request
from flask_cors import CORS
import MainAPI.ConnectSQL


connect = MainAPI.ConnectSQL.SqlFunction()

app = Flask(__name__)
CORS(app)


@app.route("/admin/list-staff", methods=['GET'])
def get_list_staff():
    data = connect.get_list_staff()
    return jsonify(data)


@app.route("/staff/login", methods=['POST'])
def login_staff():
    username = request.json['username']
    password = request.json['password']
    if connect.check_login_staff(username, password):
        list_staff = connect.get_list_staff()
        for staff in list_staff:
            if staff['username'] == username:
                data = {'result': True, 'info': staff}
                return jsonify(data)
    return jsonify({'result': False, 'info': 'Sai tên đăng nhập hoặc mật khẩu'})


@app.route("/staff/change-password", methods=['POST'])
def change_password_staff():
    username = request.json['username']
    new_pass = request.json['new_pass']
    old_pass = request.json['old_pass']
    if connect.change_password_staff(username, old_pass, new_pass):
        return jsonify({'result': True, 'info': 'Đổi mật khẩu thành công'})
    return jsonify({'result': False, 'info': 'Đổi mật khẩu thất bại'})


@app.route("/person/change-info", methods=['POST'])
def change_info_person():
    first_name = request.json['first_name']
    last_name = request.json['last_name']
    gender = request.json['gender']
    identity_card = request.json['identity_card']
    email = request.json['email']
    phone_num = request.json['phone_num']
    dob = request.json['date_of_birth']
    address = request.json['address']
    id_person = request.json['id_customer']
    if connect.update_info(first_name, last_name, gender, identity_card, email, phone_num, dob, address, id_person):
        return jsonify({'result': True, 'info': 'Cập nhật thông tin cá nhân thành công'})
    return jsonify({'result': False, 'info': 'Cập nhật thông tin thất bại'})


@app.route("/customer/login", methods=['POST'])
def login_customer():
    username = request.json['username']
    password = request.json['password']
    if connect.check_login_customer(username, password):
        list_customer = connect.get_list_customer()
        for customer in list_customer:
            if customer['username'] == username:
                data = {'result': True, 'info': customer}
                return jsonify(data)
    return jsonify({'result': False, 'info': 'Sai tên đăng nhập hoặc mật khẩu'})


@app.route("/customer/register", methods=['POST'])
def register_customer():
    # not completed
    # 2 case: old customer: customer who had bought at shop =>> insert new customer base on history info
    # new customer -> insert new
    first_name = request.json['first_name']
    last_name = request.json['last_name']
    gender = request.json['gender']
    identity_card = request.json['identity_card']
    email = request.json['email']
    phone_num = request.json['phone_num']
    dob = request.json['date_of_birth']
    address = request.json['address']
    username = request.json['username']
    password = request.json['password']
    if connect.insert_customer(first_name, last_name, gender, identity_card, email, phone_num, dob,
                               address, username, password):
        list_customer = connect.get_list_customer()
        for customer in list_customer:
            if customer['username'] == username:
                data = {'result': True, 'info': customer}
                return jsonify(data)
    # else:
    #     data = {}
    #     list_customer = connect.get_list_customer()
    #     for customer in list_customer:
    #         if customer['email'] == email:
    #             data['result'] = False
    #             data['info'] = 'Email đã được sử dụng'
    #             return jsonify(data)
    #         if customer['phone_num'] == phone_num:
    #             data['result'] = False
    #             data['info'] = 'Số điện thoại đã được sử dụng'
    #             return jsonify(data)
    return jsonify({'result': False, 'info': 'Đăng kí thất bại'})


if __name__ == '__main__':
    app.run(port=5000)
