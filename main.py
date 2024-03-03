import pymysql
import matplotlib.pyplot as plt
from matplotlib.backends.backend_agg import FigureCanvasAgg as FigureCanvas
from io import BytesIO

# 连接数据库
db = pymysql.connect(host='121.41.21.243', user='user1', passwd='cyh142857NZ.', port=3306, db='mydb')

# 开启一个游标cursor
cursor = db.cursor()

# 获取数据表里的所有数据
sql = 'select * from time_data'

# 执行sql中的语句
cursor.execute(sql)

# 将获取到的sql数据全部显示出来
result = cursor.fetchall()

# 定义需要用上的空数据数组，然后通过遍历数据库的数据将数据附上去
xname = []
ynum = []

# 遍历表里的数据
for x in result:
    ynum.append(int(x[0]))

for i in range(0,len(ynum)):
    xname.append(i)

# 创建一个figure（一个窗口）来显示折线图
fig, ax = plt.subplots(figsize=(15,10))
ax.plot(xname, ynum)
for x, y in enumerate(ynum):
    ax.text(x, y, '%s'% y)

# 将图形转换为字节流
canvas = FigureCanvas(fig)
buf = BytesIO()
canvas.print_png(buf)
buf.seek(0)

# 将字节流数据插入数据库
image_data = buf.getvalue()
sql_insert = "INSERT INTO image_data (image) VALUES (%s)"
cursor.execute(sql_insert, (image_data,))

# 提交更改
db.commit()

# 关闭游标和数据库连接
cursor.close()
db.close()






