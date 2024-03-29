import pyodbc
import OtherFunctions.ExtraFunction
import Configuration.ConfigFlag

config_flag = Configuration.ConfigFlag
extra_function = OtherFunctions.ExtraFunction

connect = pyodbc.connect(
    "Driver={SQL Server Native Client 11.0};"
    "Server=WINDOWS-A251ALV;"
    "Database=MobileShop;"
    "Trusted_Connection=yes;"
)


class SqlFunction:
    def __init__(self):
        self.func = connect.cursor()

    def insert_customer(self, first_name, last_name, gender, identity_card, email, phone_num, day_of_birth,
                        address, username, password):
        try:
            cursor = self.func
            sql = """
                SET NOCOUNT ON;
                DECLARE @RC int;
                exec @RC = InsertCustomer ?, ?, ?, ?, ?, ?, ?, ?, ?, ?;
                SELECT @RC AS rc;
            """
            values = (first_name, last_name, gender, identity_card, email, phone_num,
                      day_of_birth, address, username, extra_function.hash_password(password))
            cursor.execute(sql, values)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def insert_staff(self, first_name, last_name, gender, identity_card, email, phone_num, day_of_birth,
                     address, username, password, salary, role):
        try:
            cursor = self.func
            sql = """
                SET NOCOUNT ON;
                DECLARE @RC int;
                exec @RC = InsertStaff ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?;
                SELECT @RC as rc;
            """
            values = (first_name, last_name, gender, identity_card, email, phone_num, day_of_birth, address,
                      username, extra_function.hash_password(password), salary, role)
            cursor.execute(sql, values)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def insert_supplier(self, name, phone, address):
        try:
            cursor = self.func
            cursor.execute('exec InsertSupplier ?, ?, ?', name, phone, address)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def check_existed_user(self, user, type_user=config_flag.type_customer):
        """
        :param type_user: type_customer or type_staff
        :param user: user need to check
        :return: True if user existed, else False
        """
        cursor = self.func
        if type_user == config_flag.type_customer:
            cursor.execute('exec CheckExistedCustomerUser ?', user)
        else:
            cursor.execute('exec CheckExistedStaffUser ?', user)
        ans = False
        for r in cursor:
            if r[0] == 1:
                ans = True
            break
        cursor.commit()
        return ans

    def get_current_staff_password(self, user):
        try:
            cursor = self.func
            cursor.execute('exec GetPasswordStaff ?', user)
            ans = ''
            for r in cursor:
                ans = r[0]
            cursor.commit()
            return ans
        except Exception as ex:
            print(ex)
            return ''

    def get_customer_email_by_username(self, username):
        try:
            email = ''
            cursor = self.func
            cursor.execute('exec GetCustomerEmailByUserName ?', username)
            for row in cursor:
                email = row[0]
            cursor.commit()
            return email
        except Exception as ex:
            print(ex)
            return ''

    def reset_password_staff(self, username, password):
        try:
            cursor = self.func
            cursor.execute('exec ChangePasswordStaff ?, ?', username, password)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def change_password_staff(self, user, old_pass, new_pass):
        try:
            if self.check_existed_user(user, config_flag.type_staff):
                current_pass = self.get_current_staff_password(user)
                if extra_function.hash_password(old_pass) == current_pass:
                    return self.reset_password_staff(user, extra_function.hash_password(new_pass))
                else:
                    return False
            else:
                return False
        except Exception as ex:
            print(ex)
            return False

    def get_current_customer_password(self, user):
        try:
            cursor = self.func
            cursor.execute('exec GetPasswordCustomer ?', user)
            ans = ''
            for r in cursor:
                ans = r[0]
            cursor.commit()
            return ans
        except Exception as ex:
            print(ex)
            return ''

    def reset_password_customer(self, username, password):
        try:
            cursor = self.func
            cursor.execute('exec ChangePasswordCustomer ?, ?', username, password)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def change_password_customer(self, user, old_pass, new_pass):
        try:
            if self.check_existed_user(user, config_flag.type_customer):
                current_pass = self.get_current_customer_password(user)
                if extra_function.hash_password(old_pass) == current_pass:
                    return self.reset_password_customer(user, extra_function.hash_password(new_pass))
                else:
                    return False
            else:
                return False
        except Exception as ex:
            print(ex)
            return False

    def update_info(self, first_name, last_name, gender, identity_card, email, phone_num, dob, address, id_person):
        try:
            cursor = self.func
            sql = """
                SET NOCOUNT ON;
                DECLARE @RC int;
                EXEC @RC = ChangeInfoPerson ?, ?, ?, ?, ?, ?, ?, ?, ?;
                SELECT @RC AS rc;
            """
            values = (first_name, last_name, gender, identity_card, email, phone_num, dob, address, id_person)
            cursor.execute(sql, values)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def get_list_customer(self):
        try:
            cursor = self.func
            cursor.execute('select * from GetListCustomer()')
            ans = []
            for r in cursor:
                tmp = {'id': r[0], 'last_name': r[1], 'first_name': r[2], 'gender': r[3],
                       'identity_card': r[4], 'email': r[5], 'phone_num': r[6], 'day_of_birth': str(r[7]),
                       'address': r[8], 'username': r[9], 'is_active': r[11]}
                ans.append(tmp)
            cursor.commit()
            return ans
        except Exception as ex:
            print(ex)
            return []

    def get_list_staff(self):
        try:
            cursor = self.func
            cursor.execute('select * from GetListStaff()')
            ans = []
            for r in cursor:
                tmp = {'id': r[0], 'last_name': r[1], 'first_name': r[2], 'gender': r[3],
                       'identity_card': r[4], 'email': r[5], 'phone_num': r[6], 'day_of_birth': str(r[7]),
                       'address': r[8], 'username': r[9], 'role_id': r[11],
                       'role_name': r[12], 'salary': float(r[13]), 'is_deleted': r[14]}
                ans.append(tmp)
            cursor.commit()
            return ans
        except Exception as ex:
            print(ex)
            return []

    def delete_staff(self, id_staff):
        try:
            staff_list = self.get_list_staff()
            for staff in staff_list:
                if id_staff == staff['id']:
                    cursor = self.func
                    cursor.execute('exec DeleteStaff ?', id_staff)
                    cursor.commit()
                    return True
            return False
        except Exception as ex:
            print(ex)
            return False

    def check_login_staff(self, username, password):
        try:
            cursor = self.func
            cursor.execute("select dbo.CheckLoginStaff(?, ?)", username, extra_function.hash_password(password))
            ans = 0
            for r in cursor:
                ans = r[0]
                break
            cursor.commit()
            if ans == 1:
                return True
            else:
                return False
        except Exception as ex:
            print(ex)
            return False

    def check_login_customer(self, username, password):
        try:
            cursor = self.func
            cursor.execute("select dbo.CheckLoginCustomer(?, ?)", username, extra_function.hash_password(password))
            ans = 0
            for r in cursor:
                ans = r[0]
                break
            cursor.commit()
            if ans == 1:
                return True
            else:
                return False
        except Exception as ex:
            print(ex)
            return False

    def insert_role_staff(self, role_name):
        try:
            cursor = self.func
            cursor.execute('exec InsertRole ?', role_name)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def active_customer(self, email):
        try:
            cursor = self.func
            cursor.execute("exec ActiveCustomer ?", email)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def insert_phone(self, phone_name, phone_type, phone_description, quantity, img, color, price):
        try:
            cursor = self.func
            sql = """
                            SET NOCOUNT ON;
                            DECLARE @RC int;
                            exec InsertPhone ?, ?, ?, ?, ?, ?, ?;
                            SELECT @RC as rc;
                        """
            values = (phone_name, phone_type, phone_description,
                      quantity, img, color, price)
            cursor.execute(sql, values)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def insert_phone_type(self, name_phone_type):
        try:
            cursor = self.func
            cursor.execute("exec InsertPhoneType ?", name_phone_type)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def insert_phone_color(self, color):
        try:
            cursor = self.func
            cursor.execute("exec InsertPhoneColor ?", color)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def get_list_phone(self):
        try:
            cursor = self.func
            cursor.execute('select * from GetListPhone()')
            ans = []
            for r in cursor:
                temp = {'id': r[0], 'phone_name': r[1], 'phone_type': r[2],
                        'type_phone_name': r[3], 'phone_description': r[4], 'quantity': r[5],
                        'img': r[6], 'id_color': r[7], 'color': r[8], 'price': float(r[9])}
                ans.append(temp)
            cursor.commit()
            return ans
        except Exception as ex:
            print(ex)
            return []

    def get_list_phone_type(self):
        try:
            cursor = self.func
            cursor.execute('select * from GetListPhoneType()')
            ans = []
            for r in cursor:
                ans.append({'id': r[0], 'type_phone_name': r[1]})
            cursor.commit()
            return ans
        except Exception as ex:
            print(ex)
            return []

    def get_list_phone_color(self):
        try:
            cursor = self.func
            cursor.execute('select * from GetListPhoneColor()')
            ans = []
            for r in cursor:
                ans.append({'id': r[0], 'phone_color': r[1]})
            cursor.commit()
            return ans
        except Exception as ex:
            print(ex)
            return []

    def update_phone(self, phone_name, phone_type, phone_description, quantity, img, color, price, id_phone):
        try:
            cursor = self.func
            sql = """
                    SET NOCOUNT ON;
                    DECLARE @RC int;
                    exec @RC = UpdatePhone ?, ?, ?, ?, ?, ?, ?, ?;
                    select @RC AS rc;
            """
            values = (phone_name, phone_type, phone_description, quantity, img, color, price, id_phone)
            cursor.execute(sql, values)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def update_staff(self, role_id, salary, staff_id):
        try:
            cursor = self.func
            sql = """
                SET NOCOUNT ON;
                DECLARE @RC int;
                EXEC @RC = UpdateStaff ?, ?, ?;
                SELECT @RC as rc;
            """
            values = (role_id, salary, staff_id)
            cursor.execute(sql, values)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def get_list_role(self):
        try:
            cursor = self.func
            cursor.execute('select * from GetListRole()')
            ans = []
            for r in cursor:
                ans.append({'role_id': r[0], 'role_name': r[1]})
            cursor.commit()
            return ans
        except Exception as ex:
            print(ex)
            return []

    def insert_customer_order(self, customer_id, address, staff_id, note,
                              phone_id_list: list, quantity_list: list, price_list: list):
        try:
            cursor = self.func

            # insert order info
            sql_order = """
                EXEC InsertCustomerOrder ?, ?, ?, ?;
            """
            values = (customer_id, address, staff_id, note)
            cursor.execute(sql_order, values)
            order_id = -1

            # get id of new order
            for r in cursor:
                order_id = r[0]
                print(order_id)
                break

            # insert order detail
            sql_order_detail = """
                            SET NOCOUNT ON;
                            DECLARE @RC int;
                            EXEC @RC = InsertCustomerOrderDetail ?, ?, ?, ?;
                            SELECT @RC as rc;
                        """
            for index in range(0, len(phone_id_list)):
                values = (order_id, phone_id_list[index], quantity_list[index], price_list[index])
                cursor.execute(sql_order_detail, values)

            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def update_customer_order(self, order_id, staff_id, status_id):
        try:
            cursor = self.func
            sql = """
                SET NOCOUNT ON;
                DECLARE @RC int;
                EXEC @RC = UpdateCustomerOrder ?, ?, ?;
                SELECT @RC as rc;
            """
            values = (order_id, staff_id, status_id)
            cursor.execute(sql, values)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    def get_list_history_order_customer(self, customer_id):
        try:
            cursor = self.func
            cursor.execute("select * from GetListHistoryCustomerOrder(?) order by id", customer_id)
            data = []
            for row in cursor:
                data.append({'id_order': row[0], 'created_date': str(row[1]),
                             'name': row[2], 'address': row[3], 'note': row[4],
                             'status_id': row[5], 'status_name': row[6],
                             'phone_id': row[7], 'phone_name': row[8], 'image': row[9],
                             'quantity': row[10], 'price': float(row[11])})
            cursor.commit()
            data_output = format_list_history_order_customer(data)
            return data_output

        except Exception as ex:
            print(ex)
            return []

    def get_list_all_order_customer(self, from_date, to_date):
        try:
            cursor = self.func
            cursor.execute("select * from dbo.GetListAllOrder(?, ?) order by id", from_date, to_date)
            data = []
            for row in cursor:
                data.append({'id_order': row[0], 'created_date': str(row[1]),
                             'name': row[2], 'address': row[3], 'note': row[4],
                             'status_id': row[5], 'status_name': row[6],
                             'phone_id': row[7], 'phone_name': row[8], 'image': row[9],
                             'quantity': row[10], 'price': float(row[11])})
            cursor.commit()
            output_report = format_list_history_order_customer(data)
            return output_report
        except Exception as ex:
            print(ex)
            return []


def format_list_history_order_customer(list_data):
    if len(list_data) == 0:
        return []
    if len(list_data) == 1:
        return [{'id_order': list_data[-1]['id_order'],
                 'created_date': str(list_data[-1]['created_date']),
                 'name': list_data[-1]['name'],
                 'address': list_data[-1]['address'],
                 'note': list_data[-1]['note'],
                 'status_id': list_data[-1]['status_id'],
                 'status_name': list_data[-1]['status_name'],
                 'list_order': [{'phone_id': list_data[-1]['phone_id'], 'phone_name': list_data[-1]['phone_name'],
                                 'image': list_data[-1]['image'], 'quantity': list_data[-1]['quantity'],
                                 'price': float(list_data[-1]['price'])}]}]

    history_data = []
    list_order_detail = []
    for i in range(len(list_data) - 1):
        if list_data[i]['id_order'] == list_data[i + 1]['id_order']:
            list_order_detail.append({'phone_id': list_data[i]['phone_id'], 'phone_name': list_data[i]['phone_name'],
                                      'image': list_data[i]['image'],
                                      'quantity': list_data[i]['quantity'], 'price': float(list_data[i]['price'])})
            if i == len(list_data) - 2:
                list_order_detail.append({'phone_id': list_data[-1]['phone_id'],
                                          'phone_name': list_data[-1]['phone_name'], 'image': list_data[-1]['image'],
                                          'quantity': list_data[-1]['quantity'], 'price': float(list_data[-1]['price'])})
                history_data.append({'id_order': list_data[i]['id_order'],
                                     'created_date': str(list_data[i]['created_date']),
                                     'name': list_data[i]['name'],
                                     'address': list_data[i]['address'],
                                     'note': list_data[i]['note'],
                                     'status_id': list_data[i]['status_id'],
                                     'status_name': list_data[i]['status_name'],
                                     'list_order': list_order_detail})
        else:
            list_order_detail.append({'phone_id': list_data[i]['phone_id'], 'phone_name': list_data[i]['phone_name'],
                                      'image': list_data[i]['image'], 'quantity': list_data[i]['quantity'],
                                      'price': float(list_data[i]['price'])})
            history_data.append({'id_order': list_data[i]['id_order'],
                                 'created_date': str(list_data[i]['created_date']),
                                 'name': list_data[i]['name'],
                                 'address': list_data[i]['address'],
                                 'note': list_data[i]['note'],
                                 'status_id': list_data[i]['status_id'],
                                 'status_name': list_data[i]['status_name'],
                                 'list_order': list_order_detail})
            list_order_detail = []
            if i == len(list_data) - 2:
                list_order_detail = [{'phone_id': list_data[-1]['phone_id'], 'phone_name': list_data[-1]['phone_name'],
                                      'image': list_data[-1]['image'], 'quantity': list_data[-1]['quantity'],
                                      'price': float(list_data[-1]['price'])}]
                history_data.append({'id_order': list_data[-1]['id_order'],
                                     'created_date': str(list_data[-1]['created_date']),
                                     'name': list_data[-1]['name'],
                                     'address': list_data[-1]['address'],
                                     'note': list_data[-1]['note'],
                                     'status_id': list_data[-1]['status_id'],
                                     'status_name': list_data[-1]['status_name'],
                                     'list_order': list_order_detail})

    return history_data


# Test Function
sql_connect = SqlFunction()
# print(sql_connect.insert_customer('Ngọc', 'Phạm Thị Bích', 'Nữ', '197584732', 'ngocngungoc@gmail.com', '0982312312',
#                                   '2003-04-09', 'Thái Bình', 'ngocngoc', 'ngoc123'))
# print(sql_connect.insert_staff('Trung', 'Nguyễn Đình', 'Nam', '1010101010', 'trungnd224@gmail.com', '0123456888',
#                                '1980-03-21', 'Hà Tây', 'trungnd224', '123', 1000000, 3))
# print(sql_connect.get_current_staff_password('tramskt1'))
# print(sql_connect.test())
# print(sql_connect.check_existed_user('baobao', 0))
# print(sql_connect.change_password_customer('ngocngoc', 'ngoc123', 'abc1'))
# print(sql_connect.change_password_staff('tramskt1', '123', '456'))
# print(sql_connect.change_password_staff('hoaihoai1', '1234', '456'))
# print(sql_connect.get_list_customer())
# print(sql_connect.get_list_staff())
# print(sql_connect.delete_staff('STAFF_1'))
# print(sql_connect.check_login_staff('hoaihoai1', '123'))
# print(sql_connect.active_customer('ngocngungoc@gmail.com'))
# print(sql_connect.check_login_customer('ngocngoc', 'abc1'))
# print(sql_connect.insert_phone_type('Oppo'))
# print(sql_connect.insert_phone('IPhone X', 1, 'Xinh đẹp tuyệt vời', 10, 'a', 'Trắng', 30000000))
# print(sql_connect.get_list_phone())
# print(sql_connect.get_list_phone_type())
# print(sql_connect.update_phone('Iphone 11', 1, 'Xinh ', 20, 'a', 'Xanh', 30000000, 2))
# print(sql_connect.insert_supplier('Học viện Công nghệ Bưu Chính Viễn Thông TPHCM', '0987228173', '97 Man Thiện'))
# print(sql_connect.update_info('Ngọc', 'Phạm Thị Bích', 'Nữ', '123827389', 'ngocngungoc@gmail.com', '0922132221',
#                               '2003-04-09', 'Thái Bình', 'CUSTOMER_7'))
# print(sql_connect.update_staff(3, 2500000, 'STAFF_3'))
# sql_connect.get_list_all_order_customer('2021-07-01', '2021-12-12')
# print(sql_connect.get_list_role())
