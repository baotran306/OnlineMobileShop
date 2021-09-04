import pyodbc

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
                      day_of_birth, address, username, password)
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
                      username, password, salary, role)
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

    def check_existed_user(self, user, type_user=0):
        """
        :param type_user: 0 or 1, 0 is customer, 1 is staff
        :param user: user need to check
        :return: True if user existed, else False
        """
        cursor = self.func
        if type_user == 0:
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

    def change_password_staff(self, user, old_pass, new_pass):
        try:
            if self.check_existed_user(user, 1):
                current_pass = self.get_current_staff_password(user)
                if old_pass == current_pass:
                    cursor = self.func
                    cursor.execute('exec ChangePasswordStaff ?, ?', user, new_pass)
                    cursor.commit()
                    return True
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

    def change_password_customer(self, user, old_pass, new_pass):
        try:
            if self.check_existed_user(user, 0):
                current_pass = self.get_current_customer_password(user)
                if old_pass == current_pass:
                    cursor = self.func
                    cursor.execute('exec ChangePasswordCustomer ?, ?', user, new_pass)
                    cursor.commit()
                    return True
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
            cursor.execute('exec ChangeInfoPerson ?, ?, ?, ?, ?, ?, ?, ?, ?', first_name, last_name, gender,
                           identity_card, email, phone_num, dob, address, id_person)
            cursor.commit()
            return True
        except Exception as ex:
            print(ex)
            return False

    # not complete
    def delete_staff(self, id_staff):
        try:
            cursor = self.func
            pass
        except Exception as ex:
            print(ex)
            return False


# Test Function
sql_connect = SqlFunction()
# print(sql_connect.insert_customer('Liên', 'Trần Bích', 'Nam', '123456789', 'lienlien111@gmail.com', '0111111111',
#                                   '2004-04-09', 'Thái Bình', 'lienlien111', '123'))
# print(sql_connect.insert_staff('Trung', 'Nguyễn Đình', 'Nam', '1010101010', 'trungnd224@gmail.com', '0123456888',
#                                '1980-03-21', 'Hà Tây', 'trungnd224', '123', 1000000, 3))
# print(sql_connect.get_current_staff_password('tramskt1'))
#sql_connect.test()
# print(sql_connect.check_existed_user('baobao', 0))
# print(sql_connect.change_password_customer('mumumu', '12233', 'abc1'))
# print(sql_connect.change_password_staff('tramskt1', '123', '456'))
# print(sql_connect.change_password_staff('hoaihoai1', '1234', '456'))
