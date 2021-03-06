require 'net/dns/resolver'
require 'net/dns/packet'
require 'net/dns/rr'
require 'set'

#
# Check with NS dns query.
#
PlugMan.define :nsbydomain do
  author "Alessandro Tanasi"
  version "0.2.2"
  extends({ :main => [:domain] })
  requires []
  extension_points []
  params({ :description => "Return name servers for a domain." })

  def run(domain, opts = {})
    @ns = Set.new

    if opts['dns']
      dns = opts['dns'].gsub(/\s/, '').split(',')
      res = Net::DNS::Resolver.new(:nameserver => dns)
    else
      res = Net::DNS::Resolver.new
    end

    # Silence net-dns logger
    res.log_level = Net::DNS::UNKNOWN

    begin
      res.query(domain, Net::DNS::NS).answer.each do |rr|
        if rr.class == Net::DNS::RR::NS
          @ns << { :ns => rr.nsdname.gsub(/\.$/,'') }
        end
      end
    rescue
      nil
    end

    return @ns
  end

  def timeout
    return @ns
  end
end
