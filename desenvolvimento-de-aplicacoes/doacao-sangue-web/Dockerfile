FROM oven/bun:latest AS build

WORKDIR /app

COPY package.json bun.lockb* ./ 
RUN bun install --frozen-lockfile

COPY . .

RUN bun run build

FROM nginx:alpine AS prod
COPY --from=build /app/dist/doacao-sangue-web/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]