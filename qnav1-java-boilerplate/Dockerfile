FROM node
VOLUME /var/www
WORKDIR /var/www
COPY dist/ dist/
COPY bin/ bin/
COPY app.js app.js
COPY routes/ routes/
COPY package.json package.json
COPY appStartScript.sh appStartScript.sh
ENTRYPOINT ["sh", "appStartScript.sh"]
