package com.lizi;

/**
 * 题目描述：
 * 店长小明在图上画出了门店鲜食采购申请的流程图，图中有n个节点，
 * 第一个节点为流程申请人发起申请（S）节点，后续的n-1个节点均为审批节点，
 * 审批人可操作的行为有：审批同意（A）、审批拒绝（R）、审批驳回（B）。
 * 已知每一个节点只有在上一个节点审批同意之后方可进行审批，若审批节点1的下个可审批节点是2和3，
 * 只要2节点的审批人执行了审批操作，该流程就不会指向3节点。若审批驳回，流程将扭转到上一个节点操作
 * 人进行二次操作（重新发起申请或审批），若审批拒绝，流程将扭转到流程申请人节点重新发起申请。
 * 请你找出流程图中可能存在的环。若环存在，请输出一共存在多少个环，并输出路径最长的环上共经过了
 * 多少个节点。若环不存在，则输出null。所经过的节点相同的视为同一个环。
 */
public class 流程图存在多少环2 {

}
