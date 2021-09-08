package com.loud9ja.loud9ja.di

import okhttp3.Dns
import java.net.Inet4Address
import java.net.InetAddress

class DnsSelector() : Dns {
    override fun lookup(hostname: String): List<InetAddress> {
        return Dns.SYSTEM.lookup(hostname).filter { Inet4Address::class.java.isInstance(it) }
    }
}