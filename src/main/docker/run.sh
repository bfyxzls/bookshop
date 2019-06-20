docker build . --tag bookshop && docker run -d -p 80:9008 -v ~/video:/deployments/static/video bookshop
