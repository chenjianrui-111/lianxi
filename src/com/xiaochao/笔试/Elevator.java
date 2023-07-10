package com.xiaochao.笔试;

//public class Elevator {
//    /**
//     * 向上需要去的楼层
//     */
//    private boolean[] upStops;
//
//    /**
//     * 向下需要去的楼层
//     */
//    private boolean[] downStops;
//
//    /**
//     * 电梯现在所在的楼层
//     */
//    private int currentLevel;
//
//    /**
//     * 电梯现在的方向
//     */
//    private Status status;
//
//    public Elevator(int n) {
//        this.upStops = new boolean[n];
//        this.downStops = new boolean[n];
//        this.currentLevel = 0;
//        this.status = Status.IDLE;
//    }
//
//    public void handlerInternalRequest(Request request) throws Exception {
//        if(request.level <= 0 || request.level > upStops.length){
//            throw new Exception("楼层不合法");
//        }
//
//        // 无论电梯向上还是向下运行，只要你的楼层比当前楼层大，那就放入 up 队列，否则放入 down 队列
//        if(request.level - (currentLevel + 1) <= 0){
//            downStops[request.level - 1] = true;
//            if(noneRequests(upStops)){
//                status = Status.DOWN;
//            }
//        }else {
//            upStops[request.level - 1] = true;
//            if(noneRequests(downStops)){
//                status = Status.UP;
//            }
//        }
//    }
//
//    public void openGate(){
//        switch (status){
//            case UP:
//                for (int i = currentLevel; i < upStops.length; i++) {
//                    if(upStops[i]) {
//                        upStops[i]  = false;
//                        currentLevel = i;
//                        break;
//                    }
//                }
//                break;
//            case DOWN:
//                for(int i = currentLevel; i >= 0; i--) {
//                    if(downStops[i]) {
//                        downStops[i] = false;
//                        currentLevel = i;
//                        break;
//                    }
//                }
//                break;
//        }
//        System.out.println("方向是：" + status.name() + ", 楼层是：" + (currentLevel + 1) +  "开门！");
//    }
//
//    public void closeGate(){
//        System.out.println("方向是：" + status.name() + ", 楼层是：" + (currentLevel + 1) + "关门！");
//
//        if(noneRequests(upStops) && noneRequests(downStops)){
//            status = Status.IDLE;
//        }else if(noneRequests(upStops) && !noneRequests(downStops)){
//            status = Status.DOWN;
//        }else {
//            status = Status.UP;
//        }
//    }
//
//    public boolean noneRequests(boolean[] stops){
//        for (int i = 0; i < stops.length; i++) {
//            if(stops[i]){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean isSomeThingLeftInStops(){
//        for (int i = 0; i < upStops.length; i++) {
//            if(upStops[i] || downStops[i]){
//                return true;
//            }
//        }
//        return false;
//    }
//}
