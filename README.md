sudo ln -s /home/aakhmerov/work/personal/thack/configs/local.nginx.conf /etc/nginx/sites-available/thack
sudo ln -s /etc/nginx/sites-available/thack /etc/nginx/sites-enabled/thack.conf

http://hub.lh.com/hackathon
http://www-getyourguide-com-test3.gygtest.com/thack

to run backend:
mvn -DskipTests -Plaunch clean package
