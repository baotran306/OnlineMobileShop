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


@app.route("/admin/insert-role", methods=['POST'])
def insert_role():
    try:
        role_name = request.json['role_name']
        if connect.insert_role_staff(role_name):
            data = {'result': True, 'info': 'Thêm thành công'}
            return jsonify(data)
        return jsonify({'result': False, 'info': 'Thêm thất bại'})
    except Exception as ex:
        print(ex)
        return jsonify({'result': False, 'info': 'Có lỗi xảy ra'})


@app.route("/admin/list-role", methods=['GET'])
def get_list_role():
    list_role = connect.get_list_role()
    return jsonify(list_role)


@app.route("/admin/insert-staff", methods=['POST'])
def insert_staff():
    try:
        first_name = request.json['first_name']
        last_name = request.json['last_name']
        gender = request.json['gender']
        identity_card = request.json['identity_card']
        email = request.json['email']
        phone_num = request.json['phone_num']
        day_of_birth = request.json['date_of_birth']
        address = request.json['address']
        username = request.json['username']
        password = request.json['password']
        salary = request.json['salary']
        role = request.json['role_id']
        if connect.insert_staff(first_name, last_name, gender, identity_card, email, phone_num, day_of_birth,
                                address, username, password, salary, role):
            data = {'result': True, 'info': 'Thêm nhân viên thành công'}
            return jsonify(data)
        return jsonify({'result': False, 'info': 'Thêm nhân viên thất bại'})
    except Exception as ex:
        print(ex)
        return jsonify({'result': False, 'info': 'Có lỗi xảy ra'})


@app.route("/admin/update-staff", methods=['POST'])
def update_staff():
    try:
        role_id = request.json['role_id']
        salary = request.json['salary']
        staff_id = request.json['staff_id']
        if connect.update_staff(role_id, salary, staff_id):
            data = {'result': True, 'info': 'Sửa nhân viên thành công'}
            return jsonify(data)
        return jsonify({'result': False, 'info': 'Sửa thông tin nhân viên thất bại'})
    except Exception as ex:
        print(ex)
        return jsonify({'result': False, 'info': 'Có lỗi xảy ra'})


@app.route("/admin/delete-staff", methods=['POST'])
def delete_staff():
    try:
        staff_id = request.json['staff_id']
        if connect.delete_staff(staff_id):
            data = {'result': True, 'info': 'Xóa nhân viên thành công'}
            return jsonify(data)
        return jsonify({'result': False, 'info': 'Xóa nhân viên thất bại'})
    except Exception as ex:
        print(ex)
        return jsonify({'result': False, 'info': 'Có lỗi xảy ra'})


@app.route("/staff/login", methods=['POST'])
def login_staff():
    try:
        username = request.json['username']
        password = request.json['password']
        if connect.check_login_staff(username, password):
            list_staff = connect.get_list_staff()
            for staff in list_staff:
                if staff['username'] == username:
                    data = {'result': True, 'info': staff}
                    return jsonify(data)
        return jsonify({'result': False, 'info': 'Sai tên đăng nhập hoặc mật khẩu'})
    except Exception as ex:
        print(ex)
        return jsonify({'result': False, 'info': 'Có lỗi xảy ra'})


@app.route("/staff/change-password", methods=['POST'])
def change_password_staff():
    try:
        username = request.json['username']
        new_pass = request.json['new_pass']
        old_pass = request.json['old_pass']
        if connect.change_password_staff(username, old_pass, new_pass):
            return jsonify({'result': True, 'info': 'Đổi mật khẩu thành công'})
        return jsonify({'result': False, 'info': 'Đổi mật khẩu thất bại'})
    except Exception as ex:
        print(ex)
        return jsonify({'result': False, 'info': 'Có lỗi xảy ra'})


@app.route("/staff/show-info/<string:staff_id>", methods=['GET'])
def show_info_staff_by_id(staff_id):
    try:
        list_staff = connect.get_list_staff()
        for staff in list_staff:
            if staff['id'] == staff_id:
                return jsonify(staff)
        return jsonify()
    except Exception as ex:
        print(ex)
        return jsonify()


@app.route("/person/change-info", methods=['POST'])
def change_info_person():
    try:
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
    except Exception as ex:
        print(ex)
        return jsonify({'result': False, 'info': 'Có lỗi xảy ra'})


@app.route("/admin/list-customer", methods=['GET'])
def get_list_customer():
    data = connect.get_list_customer()
    return jsonify(data)


@app.route("/customer/login", methods=['POST'])
def login_customer():
    try:
        username = request.json['username']
        password = request.json['password']
        if connect.check_login_customer(username, password):
            list_customer = connect.get_list_customer()
            for customer in list_customer:
                if customer['username'] == username:
                    data = {'result': True, 'info': customer}
                    return jsonify(data)
        return jsonify({'result': False, 'info': 'Sai tên đăng nhập hoặc mật khẩu'})
    except Exception as ex:
        print(ex)
        return jsonify({'result': False, 'info': 'Có lỗi xảy ra'})


@app.route("/customer/show-info/<string:customer_id>")
def show_info_customer_by_id(customer_id):
    try:
        list_customer = connect.get_list_customer()
        for customer in list_customer:
            if customer['id'] == customer_id:
                return jsonify(customer)
        return jsonify()
    except Exception as ex:
        print(ex)
        return jsonify()


@app.route("/customer/change-password", methods=['POST'])
def change_password_customer():
    try:
        username = request.json['username']
        old_pass = request.json['old_pass']
        new_pass = request.json['new_pass']
        if connect.change_password_customer(username, old_pass, new_pass):
            data = {'result': True, 'info': 'Đổi mật khẩu thành công'}
            return jsonify(data)
        return jsonify({'result': False, 'info': 'Đổi mật khẩu thất bại'})
    except Exception as ex:
        print(ex)
        return jsonify({'result': False, 'info': 'Có lỗi xảy ra'})


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


@app.route("/customer/active-account", methods=['POST'])
def active_customer_account():
    # not competed
    try:

        verify_number = request.json['verify_number']
    except Exception as ex:
        print(ex)
        return jsonify({'result': False, 'info': 'Có lỗi xảy ra'})


if __name__ == '__main__':
    app.run(port=5000)
