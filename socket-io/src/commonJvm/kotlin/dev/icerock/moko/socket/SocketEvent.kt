/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.socket

import io.socket.client.Socket

actual sealed class SocketEvent<T> : Mapper<T> {
    actual object Connect : SocketEvent<Unit>(), Mapper<Unit> by UnitMapper() {
        override val socketIoEvents: List<String> = listOf(Socket.EVENT_CONNECT)
    }

    actual object Connecting : SocketEvent<Unit>(), Mapper<Unit> by UnitMapper() {
        override val socketIoEvents: List<String> = listOf("connecting")
    }

    actual object Disconnect : SocketEvent<Unit>(), Mapper<Unit> by UnitMapper() {
        override val socketIoEvents: List<String> = listOf(Socket.EVENT_DISCONNECT)
    }

    actual object Error : SocketEvent<Throwable>() {
        override val socketIoEvents: List<String> = listOf(
            "error",
            Socket.EVENT_CONNECT_ERROR,
            "reconnect_error"
        )

        override fun mapper(array: Array<out Any>): Throwable {
            return array[0] as Throwable
        }
    }

    actual object Message : SocketEvent<Any>() {
        override val socketIoEvents: List<String> = listOf("message")
        override fun mapper(array: Array<out Any>): Any {
            return array
        }
    }

    actual object Reconnect : SocketEvent<Unit>(), Mapper<Unit> by UnitMapper() {
        override val socketIoEvents: List<String> = listOf("reconnect")
    }

    actual object ReconnectAttempt : SocketEvent<Int>() {
        override val socketIoEvents: List<String> = listOf("reconnect_attempt")
        override fun mapper(array: Array<out Any>): Int {
            return array[0] as Int
        }
    }

    actual object Ping : SocketEvent<Unit>(), Mapper<Unit> by UnitMapper() {
        override val socketIoEvents: List<String> = listOf("ping")
    }

    actual object Pong : SocketEvent<Unit>(), Mapper<Unit> by UnitMapper() {
        override val socketIoEvents: List<String> = listOf("pong")
    }

    abstract val socketIoEvents: List<String>

    private class UnitMapper : Mapper<Unit> {
        override fun mapper(array: Array<out Any>) = Unit
    }
}
