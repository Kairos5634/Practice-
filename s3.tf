# main.tf

provider "aws" {
  region = "us-east-1"  
}

resource "aws_s3_bucket" "my_bucket" {
  bucket = "my-first-terraform-bucket"  

  tags = {
    Name        = "MyFirstTerraformBucket"
    Environment = "Dev"
  }
}