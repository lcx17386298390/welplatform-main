import request from '@/utils/request'

// 查询礼品管理列表
export function listGift(query) {
  return request({
    url: '/welplatform/gift/list',
    method: 'get',
    params: query
  })
}

// 查询礼品管理详细
export function getGift(giftId) {
  return request({
    url: '/welplatform/gift/' + giftId,
    method: 'get'
  })
}

// 新增礼品管理
export function addGift(data) {
  return request({
    url: '/welplatform/gift',
    method: 'post',
    data: data
  })
}

// 修改礼品管理
export function updateGift(data) {
  return request({
    url: '/welplatform/gift',
    method: 'put',
    data: data
  })
}

// 删除礼品管理
export function delGift(giftId) {
  return request({
    url: '/welplatform/gift/' + giftId,
    method: 'delete'
  })
}
