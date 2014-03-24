sudo ssh -i ./xdb_keypair.pem -L 55500:$1:55500 -L 55501:$1:55501 ec2-user@$1
