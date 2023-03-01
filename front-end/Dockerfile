# Build the application
FROM node:16-alpine3.15

# Creates a folder and sets the current working directory inside the image
WORKDIR /app

# COPY [host machine] [container]
# (first path is relative to the location of the Dockerfile; second path is relative to the root on the container)
COPY package.json package-lock.json ./

# Run the following `npm` commands from the terminal within the container
RUN npm install

# Copies all missing files in this local directory to the container's working directory
COPY . .

# Use this command to update files for uploading to web server
RUN npm run build

# Start the application on the provided port
CMD ["npm", "start"]