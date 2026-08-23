resource "aws_route53_zone" "my-hz" {
  name = "example.com"
}

resource "aws_route53_record" "A-record" {
  zone_id = "Z1234567890ABC"
  name    = "www.example.com"
  type    = "A"
  ttl     = 300
  records = ["192.0.2.1"]
}

output "name_servers" {
  value = aws_route53_zone.example.name_servers
}