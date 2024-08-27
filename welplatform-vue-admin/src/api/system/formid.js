import request from '@/utils/request'

// 查询用户微信数据管理列表
export function listFormid(query) {
  return request({
    url: '/welplatform/formid/list',
    method: 'get',
    params: query
  })
}

// 查询用户微信数据管理详细
export function getFormid(id) {
  return request({
    url: '/welplatform/formid/' + id,
    method: 'get'
  })
}

// 新增用户微信数据管理
export function addFormid(data) {
  return request({
    url: '/welplatform/formid',
    method: 'post',
    data: data
  })
}

// 修改用户微信数据管理
export function updateFormid(data) {
  return request({
    url: '/welplatform/formid',
    method: 'put',
    data: data
  })
}

// 删除用户微信数据管理
export function delFormid(id) {
  return request({
    url: '/welplatform/formid/' + id,
    method: 'delete'
  })
}
