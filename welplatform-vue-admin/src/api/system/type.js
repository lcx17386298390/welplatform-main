import request from '@/utils/request'

// 查询任务种类管理列表
export function listType(query) {
  return request({
    url: '/welplatform/type/list',
    method: 'get',
    params: query
  })
}

// 查询任务种类管理详细
export function getType(uid) {
  return request({
    url: '/welplatform/type/' + uid,
    method: 'get'
  })
}

// 新增任务种类管理
export function addType(data) {
  return request({
    url: '/welplatform/type',
    method: 'post',
    data: data
  })
}

// 修改任务种类管理
export function updateType(data) {
  return request({
    url: '/welplatform/type',
    method: 'put',
    data: data
  })
}

// 删除任务种类管理
export function delType(uid) {
  return request({
    url: '/welplatform/type/' + uid,
    method: 'delete'
  })
}
