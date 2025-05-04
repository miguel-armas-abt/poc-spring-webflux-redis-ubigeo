
[← Regresar](../README.md) <br>

---

## ▶️ Despliegue local

1. Generar el compilado
```sh
mvn clean install
```

2. Configurar las [variables de entorno](./variables.env) en el IDE.

2. Ejecutar aplicación


---

## ▶️ Despliegue con Docker

⚙️ Crear imagen
```shell
docker build -t miguelarmasabt/ubigeo:v1.0.1 -f ./Dockerfile .
```

⚙️ Ejecutar contenedores
```shell
docker-compose -f ./docker-compose.yaml up -d
```

⚙️ Eliminar orquestación
```shell
docker-compose -f ./docker-compose.yaml down -v
```

---

## ▶️ Despliegue con Kubernetes

⚙️ Encender Minikube
```shell
docker context use default
minikube start
```

⚙️ Crear imágenes
```shell
eval $(minikube docker-env --shell bash)
docker build -t miguelarmasabt/ubigeo:v1.0.1 -f ./Dockerfile .
docker pull redis:7
```

⚙️ Crear namespace y aplicar manifiestos
```shell
kubectl create namespace ubigeo
kubectl apply -f ./k8s.yaml -n ubigeo
kubectl apply -f ./k8s-redis.yaml -n ubigeo
```

⚙️ Eliminar orquestación
```shell
kubectl delete -f ./k8s.yaml -n ubigeo
kubectl delete -f ./k8s-redis.yaml -n ubigeo
```

⚙️ Port-forward
```shell
kubectl port-forward <pod-id> 8080:8080 -n ubigeo
```

---

[📦 core-library](./src/main/java/com/demo/poc/commons/core/package-info.java)