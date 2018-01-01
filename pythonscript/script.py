import sys
import Adafruit_DHT

humidity, temperature = Adafruit_DHT.read_retry(Adafruit_DHT.DHT11, 2)

if humidity is not None and temperature is not None:
    print('{0:0.1f},{1:0.1f}'.format(temperature, humidity));
else:
    print('Data Error')
