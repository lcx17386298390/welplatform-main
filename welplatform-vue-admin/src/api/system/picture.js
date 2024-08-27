import request from '@/utils/request'

// 查询照片管理列表
export function listPicture(query) {
  return request({
    url: '/welplatform/picture/list',
    method: 'get',
    params: query
  })
}

// 查询照片管理详细
export function getPicture(uid) {
  return request({
    url: '/welplatform/picture/' + uid,
    method: 'get'
  })
}

// 新增照片管理
export function addPicture(data) {
  return request({
    url: '/welplatform/picture',
    method: 'post',
    data: data
  })
}

// 修改照片管理
export function updatePicture(data) {
  return request({
    url: '/welplatform/picture',
    method: 'put',
    data: data
  })
}

// 删除照片管理
export function delPicture(uid) {
  return request({
    url: '/welplatform/picture/' + uid,
    method: 'delete'
  })
}
