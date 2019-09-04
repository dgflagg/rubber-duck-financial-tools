# rubber-duck-financial-tools
Tools every rubber duck needs to assess and improve their personal finances.

# Build docker image locally:
docker build -t rdft:local .

# Run container locally:
docker run --name rdft --rm -d -p 8081:8081 rdft:local

# Test running container locally:
curl http://localhost:8081/pay?salary=50000

# Install the app to a remote host group
docker run --rm -v $(pwd):/workspace -v ~/.ssh:/root/.ssh -w /workspace dgflagg/acm ansible-playbook install-rdft.yml -i test_inventory.ini