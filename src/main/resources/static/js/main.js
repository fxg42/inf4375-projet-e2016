var renderCitation = function (citation) {
  return '<li>'+ citation.contenu +' &mdash;'+ citation.auteur +'</li>'
}

var renderCitations = function (citations) {
  return '<ul>'+ citations.map(renderCitation).join('') +'</ul>'
}

var mountListeCitations = function (citations) {
  document.getElementById('liste-citations').innerHTML = renderCitations(citations)
}

var fetchAll = function () {
  fetch('/citations').then(function(resp) {
    return resp.json()
  }).then(function(data) {
    mountListeCitations(data)
  })
}

document.addEventListener('DOMContentLoaded', fetchAll)
