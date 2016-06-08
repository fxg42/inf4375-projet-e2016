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
  var xhr = new XMLHttpRequest()
  xhr.open('get', '/citations', true)
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var citations = JSON.parse(xhr.responseText)
      mountListeCitations(citations)
    }
  }
  xhr.send()
}

document.addEventListener('DOMContentLoaded', fetchAll)
