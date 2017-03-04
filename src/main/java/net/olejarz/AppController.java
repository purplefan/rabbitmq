package net.olejarz;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by purplefan on 04.03.17.
 */

@RestController
public class AppController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Receiver receiver;

    @RequestMapping("/")
    public String getContent()
    {
        return "rabbit!";
    }

    @RequestMapping(method= RequestMethod.POST, value="/queue")
    public void addToQueue(@RequestParam String id)
    {
        rabbitTemplate.convertAndSend(RabbitmqApplication.queueName, "to queue " + id);

    }

    @RequestMapping("/read")
    public String getQueue() {
        String outMsg = null;
        try {
            Message message = rabbitTemplate.receive(RabbitmqApplication.queueName);
            outMsg = new String(message.getBody());
        }
        catch (Exception e)
        {
            outMsg = "no more messages";
        }
        return outMsg;
    }

    @RequestMapping("/sendmore")
    public String sendToQueue()
    {
        int i;
        for (i=1; i<10000; i++) {
            rabbitTemplate.convertAndSend(RabbitmqApplication.queueName, "to queue " + i);
        }

        return "sent " + i + "messages";
    }
}
