import request from '@/utils/request'

// 查询积分管理列表
export function listPoint(query) {
  return request({
    url: '/welplatform/point/list',
    method: 'get',
    params: query
  })
}

// 查询积分管理详细
export function getPoint(uid) {
  return request({
    url: '/welplatform/point/' + uid,
    method: 'get'
  })
}

// 新增积分管理
export function addPoint(data) {
  return request({
    url: '/welplatform/point',
    method: 'post',
    data: data
  })
}

// 修改积分管理
export function updatePoint(data) {
  return request({
    url: '/welplatform/point',
    method: 'put',
    data: data
  })
}

// 删除积分管理
export function delPoint(uid) {
  return request({
    url: '/welplatform/point/' + uid,
    method: 'delete'
  })
}
