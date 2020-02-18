package com.nalaolla.stompchat.controller;

import com.nalaolla.stompchat.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/chat/message")
    public void message(ChatMessage chatMessage) {
        if(ChatMessage.MessageType.ENTER.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니다.");
        }

        simpMessageSendingOperations.convertAndSend("/sub/chat/room/" + chatMessage.getRoomId(), chatMessage);

    }
}
