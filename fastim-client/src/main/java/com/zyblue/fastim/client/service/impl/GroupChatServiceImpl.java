package com.zyblue.fastim.client.service.impl;

import com.zyblue.fastim.client.client.FastImClient;
import com.zyblue.fastim.client.constant.CmdType;
import com.zyblue.fastim.client.service.ImService;
import com.zyblue.fastim.common.codec.FastImMsg;
import com.zyblue.fastim.common.enumeration.MsgType;
import com.zyblue.fastim.common.pojo.message.GroupChatRequest;
import com.zyblue.fastim.common.pojo.message.GroupChatResponse;
import com.zyblue.fastim.common.util.ProtoStuffUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author will
 * @date 2021/7/12 18:03
 */
public class GroupChatServiceImpl implements ImService<GroupChatRequest> {

    private final static Logger logger = LoggerFactory.getLogger(SingleChatServiceImpl.class);

    private static final GroupChatServiceImpl singleChatService = new GroupChatServiceImpl();

    public static ImService<?> getInstance() {
        return singleChatService;
    }

    @Override
    public void received(FastImMsg fastImMsg) {
        GroupChatResponse response = ProtoStuffUtils.deserialize(fastImMsg.getData(), GroupChatResponse.class);

        /*
         * 群聊消息通过推拉结合来做
         */
    }

    @Override
    public void sendMsg(FastImMsg protocol, GroupChatRequest request) {
        logger.info("sendMsg|request:{}", request);
        protocol.setVersion(1);
        protocol.setCmd(CmdType.GROUP_CHAT.getVal());
        protocol.setMsgType(MsgType.REQUEST.getVal());
        protocol.setData(ProtoStuffUtils.serialize(request));
        FastImClient.getInstance().send(protocol, request);
    }
}
