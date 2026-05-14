

# Traccar Simulator — Teltonika FMB920 Codec 8

A Java application that simulates a Teltonika FMB920 GPS tracking device
sending real Codec 8 binary packets to a Traccar server over TCP.

Built as part of the Kanour Fleer fleet intelligence platform testing protocol.

---

## What This Does

- Opens a TCP connection to a Traccar server on port 5027
- Sends a valid IMEI handshake to identify the device
- Sends a Codec 8 binary packet containing GPS coordinates, speed and timestamp
- Traccar decodes the packet and forwards it as JSON via webhook

---

## How To Run

1. Make sure Traccar is installed and running on your machine
2. Register a test device in Traccar with IMEI: 123456789012345
3. Run TeltonikaTcpSimulator.java
4. Check your Traccar dashboard map for the device position

---

## Traccar Webhook Configuration

To enable JSON forwarding add the following to your Traccar application.xml
located at C:\Program Files\Traccar\conf\application.xml

See config/application.xml in this repository for the full configuration.

The webhook forwards every position event as a JSON HTTP POST to the
configured URL — in production this points to the Kanour Fleer Node.js API.

---

## Pipeline

Teltonika FMB920 (simulated) ↓ TCP Binary (Codec 8) — Port 5027 Traccar Server ↓ HTTP POST (JSON) Node.js Intelligence API


---

## Test Device Details

- IMEI: 123456789012345
- Coordinates: Lagos Island (6.4550, 3.3841)
- Speed: 60 km/h
- Protocol: Teltonika Codec 8
