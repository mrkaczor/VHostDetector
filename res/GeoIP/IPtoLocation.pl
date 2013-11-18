#!/usr/bin/perl 
 
use lib "Geo-IP-1.38/lib/";  
use Geo::IP; 
 
my $gi =   Geo::IP->open( "GeoLiteCity.dat", GEOIP_STANDARD ); 
 
   my $r = $gi->record_by_name($ARGV[0]); 
	 
   if ($r) { 
     print join( "\n", 
                 $r->country_code, $r->country_name) 
       . "\n"; 
   } 
   else { 
     print "Location of this IP Address is NOT defined !\n"; 
   }
