# main.tf

provider "aws" {
  region = "us-east-1"
}

resource "aws_security_group" "my_sg" {
  name        = "my-simple-sg"
  description = "Allow SSH and HTTP access"
  vpc_id      = aws_vpc.my_vpc.id  # reference your VPC resource

  ingress {
    description = "SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]  # ⚠️ open to the world - restrict to your IP in real use
  }

  ingress {
    description = "HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    description = "Allow all outbound traffic"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"  # -1 means all protocols
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "MySimpleSG"
  }
}