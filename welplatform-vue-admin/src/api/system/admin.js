import request from '@/utils/request'

// 查询admin管理列表
export function listAdmin(query) {
  return request({
    url: '/welplatform/admin/list',
    method: 'get',
    params: query
  })
}

// 查询admin管理详细
export function getAdmin(uid) {
  return request({
    url: '/welplatform/admin/' + uid,
    method: 'get'
  })
}

// 新增admin管理
export function addAdmin(data) {
  return request({
    url: '/welplatform/admin',
    method: 'post',
    data: data
  })
}

// 修改admin管理
export function updateAdmin(data) {
  return request({
    url: '/welplatform/admin',
    method: 'put',
    data: data
  })
}

// 删除admin管理
export function delAdmin(uid) {
  return request({
    url: '/welplatform/admin/' + uid,
    method: 'delete'
  })
}
