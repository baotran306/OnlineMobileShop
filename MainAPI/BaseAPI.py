from flask import Flask, jsonify, request
from flask_cors import CORS
from datetime import datetime
import MainAPI.ConnectSQL


connect = MainAPI.ConnectSQL.SqlFunction()

app = Flask(__name__)
CORS(app)
