import request from '@/utils/request'

// 查询任务管理列表
export function listTask(query) {
  return request({
    url: '/welplatform/task/list',
    method: 'get',
    params: query
  })
}

// 查询任务管理详细
export function getTask(uid) {
  return request({
    url: '/welplatform/task/' + uid,
    method: 'get'
  })
}

// 新增任务管理
export function addTask(data) {
  return request({
    url: '/welplatform/task',
    method: 'post',
    data: data
  })
}

// 修改任务管理
export function updateTask(data) {
  return request({
    url: '/welplatform/task',
    method: 'put',
    data: data
  })
}

// 删除任务管理
export function delTask(uid) {
  return request({
    url: '/welplatform/task/' + uid,
    method: 'delete'
  })
}
